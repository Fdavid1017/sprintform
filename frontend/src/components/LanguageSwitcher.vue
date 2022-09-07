<template>
  <div class="d-flex align-items-center">
    <div
      class="language-option me-1"
      :class="{ active: $i18n.locale === 'hu' }"
      @click="$i18n.locale = 'hu'"
    >
      HU
    </div>

    <span class="opacity-50">|</span>

    <div
      class="language-option ms-1"
      :class="{ active: $i18n.locale === 'en' }"
      @click="$i18n.locale = 'en'"
    >
      EN
    </div>
  </div>
</template>

<script setup></script>

<style scoped lang="scss">
@import "../assets/scss/variables";

.language-option {
  opacity: 0.5;
  cursor: pointer;
  font-size: 12px;
  transition: all 0.2s ease;
  color: $blue;

  &:hover {
    opacity: 1;
  }
}

.active {
  opacity: 1;
  font-weight: bold;
}
</style>
