<template>
  <div class="filter-container shadow p-3">
    <div class="fw-bold fs-5 mb-4">
      {{ $t("form.search") }}
    </div>

    <div class="row w-100 mx-0">
      <div class="col-4 ps-0">
        <div class="form-floating mb-3">
          <input
            v-model="filterValue.text"
            type="text"
            class="form-control"
            id="searchText"
            :placeholder="$t('form.search')"
          />
          <label for="searchText">{{ $t("form.search") }}</label>
        </div>
      </div>
      <div class="col-4">
        <div class="form-floating mb-3">
          <input
            v-model="filterValue.min"
            type="number"
            class="form-control"
            id="searchMin"
            :placeholder="$t('form.min')"
          />
          <label for="searchMin">{{ $t("form.min") }}</label>
        </div>
      </div>
      <div class="col-4 pe-0">
        <div class="form-floating mb-3">
          <input
            v-model="filterValue.max"
            type="number"
            class="form-control"
            id="searchMax"
            :placeholder="$t('form.max')"
          />
          <label for="searchMax">{{ $t("form.max") }}</label>
        </div>
      </div>
    </div>

    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="resetFilter()">
        {{ $t("form.clear") }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { computed, defineProps, defineEmits } from "vue";

const emits = defineEmits(["update:modelValue"]);

const props = defineProps({
  modelValue: {
    type: Object,
    required: true,
    default: () => ({
      text: "",
      min: 0,
      max: 0,
    }),
  },
});

const filterValue = computed({
  get: () => props.modelValue,
  set: (val) => emits("update:modelValue", val),
});

function resetFilter() {
  filterValue.value = {
    text: "",
    min: null,
    max: null,
  };
}
</script>

<style scoped></style>
