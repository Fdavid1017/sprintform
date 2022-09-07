<template>
  <div
    class="transaction-card d-flex flex-column flex-sm-row align-items-center justify-content-between"
  >
    <div
      class="d-flex flex-column flex-sm-row align-items-center text-center text-sm-start"
    >
      <transaction-category-icon
        :category="props.transaction.category"
        class="me-2"
      />

      <div>
        <div class="transaction-summary">
          {{ props.transaction.summary }}
        </div>
        <div class="transaction-paid">
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

    <div class="transaction-amount">
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
    default: () => ({
      id: "0",
      summary: "",
      category: "",
      sum: 0,
      currency: "",
      paid: "",
    }),
  },
});

const color = computed(() => {
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
@import "node_modules/bootstrap/scss/functions";
@import "node_modules/bootstrap/scss/variables";
@import "node_modules/bootstrap/scss/mixins";
@import "../assets/scss/variables";
@import "../assets/scss/mixins";

.transaction-card {
  @include panel(v-bind(color));

  padding: 10px;
  cursor: pointer;
  transition: all 0.15s ease-out;

  &:hover {
    box-shadow: 8px 8px 0px v-bind(color);
  }

  .transaction-summary,
  .transaction-amount {
    font-size: 25px;
    font-weight: 700;
  }

  .transaction-paid {
    font-size: 18px;
  }
}
</style>
