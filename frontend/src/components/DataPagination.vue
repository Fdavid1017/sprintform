<template>
  <div class="d-flex align-items-center justify-content-center">
    <div
      v-if="currentPage > 1"
      class="chevron chevron-left pagination-item"
      @click="pageItemClicked(currentPage - 1)"
    >
      <font-awesome-icon icon="fa-solid fa-chevron-left" size="xl" />
    </div>

    <div
      v-for="item in paginationItems"
      :key="item"
      :class="{ current: currentPage + 1 === item, dots: item === '...' }"
      class="pagination-item"
      @click="pageItemClicked(item - 1)"
    >
      {{ item }}
    </div>

    <div
      v-if="currentPage < totalPages - 1"
      class="chevron chevron-right pagination-item"
      @click="pageItemClicked(currentPage + 1)"
    >
      <font-awesome-icon icon="fa-solid fa-chevron-right" size="xl" />
    </div>

    <div>
      {{ itemsToShow }}
    </div>
  </div>
</template>

<script setup>
import { defineProps, computed, defineEmits } from "vue";

const emits = defineEmits(["pageChange"]);

const props = defineProps({
  currentPage: {
    type: Number,
    required: true,
    default: 0,
  },
  totalPages: {
    type: Number,
    required: true,
    default: 0,
  },
  itemsDelta: {
    type: Number,
    required: false,
    default: () => {
      return 2;
    },
  },
});

const paginationItems = computed(() => {
  const current = props.currentPage,
    last = props.totalPages,
    delta = props.itemsDelta,
    left = current - delta,
    right = current + delta + 1,
    range = [],
    rangeWithDots = [];

  let l;

  for (let i = 1; i <= last; i++) {
    if (i === 1 || i === last || (i >= left && i < right)) {
      range.push(i);
    }
  }

  for (let i of range) {
    if (l) {
      if (i - l === 2) {
        rangeWithDots.push(l + 1);
      } else if (i - l !== 1) {
        rangeWithDots.push("...");
      }
    }
    rangeWithDots.push(i);
    l = i;
  }

  return rangeWithDots;
});

function pageItemClicked(item) {
  if (item === "...") {
    return;
  }

  emits("pageChange", item);
}
</script>

<style lang="scss" scoped>
@import "../assets/scss/variables.scss";
@import "../assets/scss/mixins.scss";

.pagination-item {
  @include panel-small();

  $size: 40px;

  aspect-ratio: 1/1;
  width: $size;
  height: $size;
  cursor: pointer;
  font-weight: 900;
  transition: all 0.15s ease-in;
  margin-right: 15px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    background-color: darken($white, 20%);
    box-shadow: 5px 5px 0 $black;
  }

  &.current {
    pointer-events: none;
    background-color: darken($white, 40%);
  }

  &.dots {
    pointer-events: none;
  }
}

.chevron {
  color: $black;
}
</style>
