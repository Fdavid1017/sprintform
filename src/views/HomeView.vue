<template>
  <div class="container">
    <div v-if="isLoading" class="text-center">
      <div class="spinner-border" role="status"></div>
    </div>

    <div v-if="hasError" class="text-danger text-center fw-bold fs-5">
      {{ $t("error.wentWrong") }} {{ $t("error.tryAgain") }}
    </div>

    <transaction-filter v-model="filter" class="mb-5" />

    <div class="mb-3 fw-bold opacity-75">
      {{
        $t("home.transactions", {
          show: filteredTransactions.length,
          total: transactions.length,
        })
      }}
    </div>

    <transaction-card
      v-for="transaction in filteredTransactions"
      :key="transaction.id"
      :transaction="transaction"
      class="mb-4"
    ></transaction-card>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { getTransactions } from "@/services/transactionService";
import TransactionCard from "@/components/TransactionCard";
import TransactionFilter from "@/components/TransactionFilter";

const transactions = ref([]);
const isLoading = ref(false);
const hasError = ref(false);
const filter = ref({
  text: "",
  min: 0,
  max: 0,
});

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

  return !filter.value.max
    ? minValid
    : minValid.filter((x) => x.sum <= filter.value.max);
});
</script>

<style scoped lang="scss"></style>
