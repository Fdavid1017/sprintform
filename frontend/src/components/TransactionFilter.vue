<template>
  <div class="filter-container">
    <div class="row w-100 mx-0 mb-4">
      <div class="col-12 col-md-12 col-lg-4 p-0 pe-lg-2">
        <my-input
          v-model="filterValue.text"
          :label="$t('form.search')"
          id="search-text"
        />
      </div>
      <div class="col-12 col-md-6 col-lg-4 p-0 pe-md-2 ps-lg-2">
        <my-input
          v-model="filterValue.min"
          :label="$t('form.min')"
          id="search-min"
        />
      </div>
      <div class="col-12 col-md-6 col-lg-4 p-0 ps-md-2">
        <my-input
          v-model="filterValue.max"
          :label="$t('form.max')"
          id="search-max"
        />
      </div>
    </div>

    <div class="d-flex justify-content-end">
      <button class="my-button" @click="resetFilter()">
        {{ $t("form.clear") }}
      </button>
    </div>
  </div>
</template>

<script setup>
import MyInput from "@/components/MyInput";
import { computed, defineProps, defineEmits } from "vue";

const emits = defineEmits(["update:modelValue"]);

const props = defineProps({
  modelValue: {
    type: Object,
    required: true,
    default: () => ({
      text: "",
      min: null,
      max: null,
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

<style lang="scss" scoped>
@import "../assets/scss/variables.scss";
@import "../assets/scss/mixins.scss";

.filter-container {
  @include panel();

  padding: 10px;
}
</style>
