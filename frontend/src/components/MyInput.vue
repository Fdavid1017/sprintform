<template>
  <div class="">
    <label class="d-block" :for="id">{{ label }}</label>
    <input
      class="w-100"
      :type="type"
      :id="id"
      v-bind="$attrs"
      v-model="value"
    />
  </div>
</template>

<script setup>
import { number } from "@intlify/core-base";
import { computed, defineProps, defineEmits } from "vue";

const emits = defineEmits(["update:modelValue"]);

const props = defineProps({
  modelValue: {
    type: [String, number],
    required: true,
    default: "",
  },
  label: {
    type: String,
    required: true,
    default: "",
  },
  type: {
    type: String,
    default: "text",
  },
  id: {
    type: String,
    required: true,
    default: "input",
  },
});

const value = computed({
  get: () => props.modelValue,
  set: (val) => emits("update:modelValue", val),
});
</script>

<style lang="scss" scoped>
@import "../assets/scss/variables.scss";
@import "../assets/scss/mixins.scss";

label {
  font-weight: 700;
  font-size: 25px;
  color: $black;
}

input {
  @include panel-small();
  font-size: 18px;
  padding: 5px 10px;
  outline: none;
  transition: all 0.15s ease-in;

  &:hover,
  &:focus {
    box-shadow: 5px 5px 0px $black;
  }
}
</style>
