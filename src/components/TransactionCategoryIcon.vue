<template>
  <font-awesome-icon :icon="icon" size="xl" :style="{ color }" />
</template>

<script setup>
import { computed, defineProps } from "vue";
import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";
import getTransactionCategoryColor from "@/helpers/getTransactionCategoryColor";

const props = defineProps({
  category: {
    type: String,
    required: true,
    default: "",
  },
  colored: {
    type: Boolean,
    default: () => true,
  },
});

const icon = computed(() => {
  switch (props.category) {
    case TransactionCategoryEnum.HOUSING:
      return "fa-solid fa-house";
    case TransactionCategoryEnum.TRAVEL:
      return "fa-solid fa-car-side";
    case TransactionCategoryEnum.FOOD:
      return "fa-solid fa-burger";
    case TransactionCategoryEnum.UTILITIES:
      return "fa-solid fa-circle-question";
    case TransactionCategoryEnum.INSURANCE:
      return "fa-solid fa-file";
    case TransactionCategoryEnum.HEALTHCARE:
      return "fa-solid fa-stethoscope";
    case TransactionCategoryEnum.FINANCIAL:
      return "fa-solid fa-coins";
    case TransactionCategoryEnum.LIFESTYLE:
      return "fa-solid fa-person-running";
    case TransactionCategoryEnum.ENTERTAINMENT:
      return "fa-solid fa-circle-gamepad";
    default:
      return "fa-solid fa-folder";
  }
});

const color = computed(() => {
  if (props.colored) {
    return getTransactionCategoryColor(props.category);
  }
  return "black";
});
</script>

<style scoped lang="scss"></style>
