<template>
  <nav class="navbar navbar-light mb-5">
    <div class="container">
      <router-link to="/" class="navbar-brand d-flex align-items-center">
        <font-awesome-icon icon="fa-solid fa-coins" size="xl" />
        <div class="ms-2 d-flex flex-column justify-content-start">
          <img :src="logo" alt="sprintform Logo" class="logo" />
          <div class="logo-text">spending tracker tool</div>
        </div>
      </router-link>

      <div class="">
        <language-switcher />
      </div>
    </div>
  </nav>
</template>

<script setup>
import logo from "@/assets/images/logo.svg";
import LanguageSwitcher from "@/components/LanguageSwitcher";
</script>

<style scoped lang="scss">
@import "../assets/scss/variables";

.navbar {
  background-color: transparent;

  .navbar-brand {
    color: $blue;

    .logo {
      height: 30px;
      width: fit-content;
    }

    .logo-text {
      color: $blue;
      font-size: 11px;
    }
  }
}
</style>
