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
    case TransactionCategoryEnum.housing:
      return "fa-solid fa-house";
    case TransactionCategoryEnum.travel:
      return "fa-solid fa-car-side";
    case TransactionCategoryEnum.food:
      return "fa-solid fa-burger";
    case TransactionCategoryEnum.utilities:
      return "fa-solid fa-circle-question";
    case TransactionCategoryEnum.insurance:
      return "fa-solid fa-file";
    case TransactionCategoryEnum.healthcare:
      return "fa-solid fa-stethoscope";
    case TransactionCategoryEnum.financial:
      return "fa-solid fa-coins";
    case TransactionCategoryEnum.lifestyle:
      return "fa-solid fa-person-running";
    case TransactionCategoryEnum.entertainment:
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
