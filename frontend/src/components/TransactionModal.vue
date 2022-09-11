<template>
  <div data-bs-toggle="modal" data-bs-target="#transactionModal">
    <slot></slot>
  </div>

  <div
    class="modal fade"
    id="transactionModal"
    tabindex="-1"
    aria-labelledby="transactionModal"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ $t("modal.title") }}
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <div class="mb-4">
            <my-input
              v-model="values.summary"
              :label="$t('modal.summary')"
              :maxlength="maxSummaryLength"
              :error="errors.summary"
              type="text"
              id="summary-input"
              required
            />
          </div>

          <div class="d-flex">
            <div class="mb-4 w-75 me-2">
              <my-input
                v-model="values.sum"
                :label="$t('modal.amount')"
                :error="errors.amount"
                type="number"
                id="amount-input"
                required
              />
            </div>

            <div class="mb-4 w-25">
              <my-select
                v-model="values.currency"
                :options="currency"
                label="Currency"
                id="currency-input"
                required
              />
            </div>
          </div>

          <div class="mb-4 w-100">
            <my-select
              v-model="values.category"
              :options="categories"
              :label="$t('category.category')"
              id="category-input"
              required
            />
          </div>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="my-button-error me-2"
            @click="closeModal()"
          >
            {{ $t("modal.close") }}
          </button>
          <button
            type="button"
            class="my-button-success"
            @click="saveTransaction"
          >
            {{ $t("modal.save") }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";
import { defineEmits, defineProps, onMounted, ref, watch } from "vue";
import { Modal } from "bootstrap";
import MyInput from "./MyInput.vue";
import MySelect from "./MySelect.vue";
import CurrencyEnum from "@/enums/CurrencyEnum";

const props = defineProps({
  transactionToEdit: {
    type: Object,
    default: () => {},
  },
});

watch(
  () => props.transactionToEdit,
  (newValue) => {
    if (newValue) {
      values.value = { ...newValue };
      openModal();
    }
  }
);

const currency = Object.keys(CurrencyEnum).map((key) => {
  return {
    key,
    text: CurrencyEnum[key],
  };
});

const categories = Object.keys(TransactionCategoryEnum).map((key) => {
  return {
    key,
    text: TransactionCategoryEnum[key],
  };
});

const maxSummaryLength = 100;

const emits = defineEmits(["transactionAdd"]);

const modal = ref(undefined);
const values = ref({
  summary: "",
  sum: null,
  currency: "HUF",
  category: "housing",
});
const errors = ref({ summary: false, amount: false });

onMounted(() => {
  modal.value = new Modal("#transactionModal", {});
});

function saveTransaction() {
  errors.value = { summary: false, amount: false };

  let hasErrors = false;

  if (!values.value.summary || values.value.summary.length > maxSummaryLength) {
    errors.value.summary = true;
    hasErrors = true;
  }

  if (!values.value.sum && values.value.sum !== 0) {
    errors.value.amount = true;
    hasErrors = true;
  }

  if (!hasErrors) {
    emits("transactionAdd", values.value);
    closeModal();
  }
}

function closeModal() {
  if (modal.value) {
    modal.value.hide();
    values.value = {
      summary: "",
      sum: null,
      currency: "HUF",
      category: "HOUSING",
    };
    errors.value = { summary: false, amount: false };
  }
}

function openModal() {
  if (modal.value) {
    modal.value.show();
  }
}
</script>

<style scoped lang="scss">
@import "../assets/scss/variables.scss";
@import "../assets/scss/mixins.scss";

.modal-title {
  font-weight: 700;
  font-size: 25px;
  color: $black;
}
</style>
