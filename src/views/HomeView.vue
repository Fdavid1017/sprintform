<template>
  <div class="container">
    <div v-if="isLoading" class="text-center">
      <div class="spinner-border" role="status"></div>
    </div>

    <div v-if="hasError" class="text-danger text-center fw-bold fs-5">
      {{ $t("error.wentWrong") }} {{ $t("error.tryAgain") }}
    </div>

    <transaction-filter v-model="filter" class="mb-5" />

    <div
      class="d-flex flex-column flex-sm-row align-items-center justify-content-between mb-3"
    >
      <div class="fw-bold opacity-75">
        {{
          $t("home.transactions", {
            show: filteredTransactions.length,
            total: transactions.length,
          })
        }}
      </div>

      <transaction-modal
        :transaction-to-edit="transactionToEdit"
        @transactionAdd="transactionAdd"
      >
        <button type="button" class="btn btn-primary mt-3 mt-sm-0">
          {{ $t("modal.add") }}
        </button>
      </transaction-modal>
    </div>

    <transaction-card
      v-for="transaction in filteredTransactions"
      :key="transaction.id"
      :transaction="transaction"
      class="mb-4"
      @click="transactionToEdit = transaction"
    ></transaction-card>

    <div
      v-if="filteredTransactions.length === 0"
      class="text-sm-center fw-bold opacity-50"
    >
      {{ $t("home.noTransaction") }}
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { getTransactions } from "@/services/transactionService";
import TransactionCard from "@/components/TransactionCard";
import TransactionFilter from "@/components/TransactionFilter";
import TransactionModal from "@/components/TransactionModal";
import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";

const transactions = ref([]);
const isLoading = ref(false);
const hasError = ref(false);
const filter = ref({
  text: "",
  min: 0,
  max: 0,
});
const transactionToEdit = ref(undefined);

onMounted(() => {
  isLoading.value = true;

  getTransactions()
    .then((data) => {
      transactions.value = data;
    })
    .catch(() => {
      hasError.value = true;
    })
    .finally(() => {
      isLoading.value = false;
    });
});

const filteredTransactions = computed(() => {
  const textValid = !filter.value.text
    ? transactions.value
    : transactions.value.filter((x) =>
        x.summary.toLowerCase().includes(filter.value.text.toLowerCase())
      );

  const minValid = !filter.value.min
    ? textValid
    : textValid.filter((x) => x.sum >= filter.value.min);

  const maxValid = !filter.value.max
    ? minValid
    : minValid.filter((x) => x.sum <= filter.value.max);

  return maxValid.sort(
    (a, b) => new Date(b.paid).getTime() - new Date(a.paid).getTime()
  );
});

function transactionAdd(transaction) {
  if (transaction.id) {
    const transactionIndex = transactions.value
      .map((x) => x.id)
      .indexOf(transaction.id);

    if (transactionIndex !== -1) {
      transactions.value[transactionIndex] = transaction;
      return;
    }
  }

  const transactionValue = {
    id: Math.round(Math.random() * 1000000),
    summary: transaction.summary,
    category: TransactionCategoryEnum[transaction.category],
    sum: transaction.sum,
    currency: transaction.currency,
    paid: new Date(),
  };

  transactions.value.push(transactionValue);
}
</script>

<style scoped lang="scss"></style>
