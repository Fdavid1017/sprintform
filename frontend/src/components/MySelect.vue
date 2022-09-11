<template>
  <div class="">
    <label class="d-block" :for="id">{{ label }}</label>
    <select
      v-model="value"
      v-bind="$attrs"
      :id="id"
      class="text-capitalize w-100"
    >
      <option
        v-for="option in options"
        :key="option.key"
        :selected="index === 0"
        :value="option.key"
      >
        {{ option.text }}
      </option>
    </select>
  </div>
</template>

<script setup>
import { computed, defineProps, defineEmits } from "vue";

const emits = defineEmits(["update:modelValue"]);
const props = defineProps({
  modelValue: {
    type: [String, Number],
    required: true,
    default: "",
  },
  label: {
    type: String,
    required: true,
    default: "",
  },
  id: {
    type: String,
    required: true,
    default: "input",
  },
  options: {
    type: Array,
    required: true,
    default: () => {
      return [];
    },
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

select {
  @include panel-small();
  font-size: 18px;
  padding: 7.5px 10px;
  outline: none;
  transition: all 0.15s ease-in;

  &:hover,
  &:focus {
    box-shadow: 5px 5px 0 $black;
  }
}
</style>
