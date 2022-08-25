<template>
  <!-- Button trigger modal -->
  <div data-bs-toggle="modal" data-bs-target="#transactionModal">
    <slot></slot>
  </div>

  <!-- Modal -->
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
          <div class="form-floating mb-4">
            <input
              v-model="values.summary"
              :maxlength="maxSummaryLength"
              :class="{ 'is-invalid': errors.summary }"
              type="text"
              class="form-control"
              id="summaryInput"
              :placeholder="$t('modal.summary')"
              required
            />
            <label for="summaryInput">{{ $t("modal.summary") }}</label>
          </div>

          <div class="d-flex">
            <div class="form-floating mb-4 w-75 me-2">
              <input
                v-model="values.sum"
                :class="{ 'is-invalid': errors.amount }"
                type="number"
                class="form-control"
                id="amountInput"
                :placeholder="$t('modal.amount')"
                required
              />
              <label for="amountInput">{{ $t("modal.amount") }}</label>
            </div>

            <select
              class="form-select mb-4 w-25"
              required
              v-model="values.currency"
            >
              <option
                v-for="(value, key, index) in CurrencyEnum"
                :key="key"
                :selected="index === 0"
                :value="key"
              >
                {{ value }}
              </option>
            </select>
          </div>

          <select
            class="form-select mb-4 text-capitalize"
            required
            v-model="values.category"
          >
            <option
              v-for="(value, key, index) in TransactionCategoryEnum"
              :key="key"
              :selected="index === 0"
              :value="key"
            >
              {{ value }}
            </option>
          </select>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-outline-danger"
            @click="closeModal()"
          >
            {{ $t("modal.close") }}
          </button>
          <button
            type="button"
            class="btn btn-success"
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
import CurrencyEnum from "@/enums/CurrencyEnum";
import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";
import { defineEmits, defineProps, onMounted, ref, watch } from "vue";
import { Modal } from "bootstrap";

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

const maxSummaryLength = 100;

const emits = defineEmits(["transactionAdd"]);

const modal = ref(undefined);
const values = ref({
  summary: "",
  sum: null,
  currency: "HUF",
  category: "HOUSING",
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

<style scoped lang="scss"></style>
