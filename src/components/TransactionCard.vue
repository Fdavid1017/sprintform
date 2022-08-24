<template>
  <div
    class="transaction-card shadow p-3 d-flex align-items-center justify-content-between"
    :style="{ borderColor: borderColor }"
  >
    <div class="d-flex align-items-center">
      <transaction-category-icon
        :category="props.transaction.category"
        class="me-2"
      />

      <div class="">
        <div class="fw-bold">
          {{ props.transaction.summary }}
        </div>
        <div class="">
          {{
            paidDate.toLocaleDateString(locale, {
              year: "numeric",
              month: "2-digit",
              day: "2-digit",
            })
          }}
        </div>
      </div>
    </div>

    <div class="fw-bold fs-3">
      {{ props.transaction.sum.toLocaleString(locale) }}
      {{ getCurrencySuffix(props.transaction.currency) }}
    </div>
  </div>
</template>

<script setup>
import { computed, defineProps } from "vue";
import getTransactionCategoryColor from "@/helpers/getTransactionCategoryColor";
import TransactionCategoryIcon from "@/components/TransactionCategoryIcon";
import getCurrencySuffix from "@/helpers/getCurrencySuffix";

const props = defineProps({
  transaction: {
    type: Object,
    required: true,
    default: function () {
      return {
        id: "0",
        summary: "",
        category: "",
        sum: 0,
        currency: "",
        paid: "",
      };
    },
  },
});

const borderColor = computed(() => {
  return getTransactionCategoryColor(props.transaction.category);
});

const paidDate = computed(() => {
  return new Date(props.transaction.paid);
});
</script>

<script>
export default {
  computed: {
    locale() {
      return this.$i18n.locale === "hu" ? "hu-HU" : "en-US";
    },
  },
};
</script>

<style scoped lang="scss">
.transaction-card {
  border-left: 8px solid;
}
</style>
