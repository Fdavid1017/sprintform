<template>
  <div class="container">
    <div v-if="isLoading" class="text-center">
      <div class="spinner-border" role="status"></div>
    </div>

    <div v-if="hasError" class="text-danger text-center fw-bold fs-5">
      {{ $t("error.wentWrong") }} {{ $t("error.tryAgain") }}
    </div>

    <transaction-card
      v-for="transaction in transactions"
      :key="transaction.id"
      :transaction="transaction"
      class="mb-4"
    ></transaction-card>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getTransactions } from "@/services/transactionService";
import TransactionCard from "@/components/TransactionCard";

const transactions = ref([]);
const isLoading = ref(false);
const hasError = ref(false);

onMounted(() => {
  isLoading.value = true;

  getTransactions()
    .then((data) => {
      console.log(data);
      transactions.value = data;
    })
    .catch(() => {
      hasError.value = true;
    })
    .finally(() => {
      isLoading.value = false;
    });
});
</script>

<style scoped lang="scss"></style>
