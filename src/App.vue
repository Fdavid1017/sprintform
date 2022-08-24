<template>
  <navbar-component></navbar-component>

  <router-view />
</template>

<script setup>
import NavbarComponent from "@/components/shared/NavbarComponent";
</script>

<style lang="scss">
@import "assets/scss/variables";

#app {
  font-family: $rubik;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
