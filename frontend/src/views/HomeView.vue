<template>
  <div v-if="isLoading" class="text-center">
    <div class="spinner-border" role="status"></div>
  </div>

  <div v-else class="container">
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
            current: transactionsData.pageNumber + 1,
            total: transactionsData.totalPages,
          })
        }}
      </div>

      <!-- <transaction-modal
        :transaction-to-edit="transactionToEdit"
        @transactionAdd="transactionAdd"
      >
        <button type="button" class="my-button mt-3 mt-sm-0">
          {{ $t("modal.add") }}
        </button>
      </transaction-modal> -->
    </div>

    <transaction-card
      v-for="transaction in transactionsData?.content"
      :key="transaction.id"
      :transaction="transaction"
      class="mb-4"
      @click="transactionToEdit = transaction"
    ></transaction-card>

    <!-- <div
      v-if="filteredTransactions.length === 0"
      class="text-sm-center fw-bold opacity-50"
    >
      {{ $t("home.noTransaction") }}
    </div> -->

    <data-pagination
      :current-page="transactionsData.pageNumber"
      :total-pages="transactionsData.totalPages"
      class="mt-5"
      @pageChange="pageChange"
    />
  </div>
</template>

<!-- eslint-disable no-unused-vars -->
<script setup>
import { onMounted, ref } from "vue";
import { getTransactions } from "@/services/transactionService";
import TransactionCard from "@/components/TransactionCard";
import TransactionFilter from "@/components/TransactionFilter";
import TransactionModal from "@/components/TransactionModal";
import DataPagination from "@/components/DataPagination.vue";

const transactionsData = ref([]);
const isLoading = ref(true);
const hasError = ref(false);
const filter = ref({
  text: "",
  min: null,
  max: null,
});

const transactionToEdit = ref(undefined);

const pageSize = 5;

onMounted(() => {
  loadTransactions(0, pageSize);
});

function loadTransactions(page, size) {
  isLoading.value = true;

  getTransactions(page, size)
    .then((data) => {
      transactionsData.value = data;
      console.log(data);
    })
    .catch(() => {
      hasError.value = true;
    })
    .finally(() => {
      isLoading.value = false;
    });
}

function pageChange(page) {
  loadTransactions(page, pageSize);
}

function transactionAdd(transaction) {
  console.log(transaction);
  // if (transaction.id) {
  //   const transactionIndex = transactions.value.content
  //     .map((x) => x.id)
  //     .indexOf(transaction.id);

  //   if (transactionIndex !== -1) {
  //     transactions.value.content[transactionIndex] = transaction;
  //     return;
  //   }
  // }

  // const transactionValue = {
  //   id: Math.round(Math.random() * 1000000),
  //   summary: transaction.summary,
  //   category: TransactionCategoryEnum[transaction.category],
  //   sum: transaction.sum,
  //   currency: transaction.currency,
  //   paid: new Date(),
  // };

  // transactions.value.push(transactionValue);
}
</script>

<style scoped lang="scss"></style>
