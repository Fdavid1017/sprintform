<template>
  <div v-if="isLoading" class="text-center">
    <div class="spinner-border" role="status"></div>
  </div>

  <div class="container">
    <div v-if="hasError" class="text-danger text-center fw-bold fs-5">
      {{ $t("error.wentWrong") }} {{ $t("error.tryAgain") }}
    </div>

    <transaction-filter class="mb-5" @search="searchTransactions" />

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

      <transaction-modal
        :transaction-to-edit="transactionToEdit"
        @transactionAdd="transactionAdd"
      >
        <button type="button" class="my-button mt-3 mt-sm-0">
          {{ $t("modal.add") }}
        </button>
      </transaction-modal>
    </div>

    <transaction-card
      v-for="transaction in transactionsData?.content"
      :key="transaction.id"
      :transaction="transaction"
      class="mb-4"
      @click="transactionToEdit = transaction"
    ></transaction-card>

    <div
      v-if="transactionsData?.content === 0"
      class="text-sm-center fw-bold opacity-50"
    >
      {{ $t("home.noTransaction") }}
    </div>

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
import {
  addTransaction,
  getTransactions,
  editTransaction,
  searchTransaction,
} from "@/services/transactionService";
import TransactionCard from "@/components/TransactionCard";
import TransactionFilter from "@/components/TransactionFilter";
import TransactionModal from "@/components/TransactionModal";
import DataPagination from "@/components/DataPagination.vue";

const transactionsData = ref([]);
const isLoading = ref(true);
const hasError = ref(false);
const transactionToEdit = ref(undefined);
const pageSize = 5;

let filter = {};

onMounted(() => {
  loadTransactions(0, pageSize);
});

function loadTransactions(page, size) {
  isLoading.value = true;

  searchTransaction(filter, page, size)
    .then((data) => {
      transactionsData.value = data;
    })
    .catch(() => {
      hasError.value = true;
    })
    .finally(() => {
      isLoading.value = false;
    });
}

function searchTransactions(tf) {
  filter = tf;
  loadTransactions(0, pageSize);
}

function pageChange(page) {
  searchTransaction(filter, page, pageSize);
}

function transactionAdd(transaction) {
  console.log(transaction);
  if (transaction.id) {
    // Edit transaction
    editTransaction(transaction).then((data) => {
      for (let i = 0; i < transactionsData.value.content.length; i++) {
        if (transactionsData.value.content[i].id === data.id) {
          transactionsData.value.content[i] = data;
        }
      }
    });

    return;
  }

  // Add transaction
  addTransaction(transaction).then((data) => {
    loadTransactions(0, transactionsData.value.size);
  });
}
</script>

<style scoped lang="scss"></style>
