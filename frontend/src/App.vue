<template>
  <navbar-component></navbar-component>

  <router-view />
</template>

<script setup>
import NavbarComponent from "@/components/NavbarComponent";
</script>

<style lang="scss">
@import "assets/scss/variables";
@import "assets/scss/mixins";

#app {
  font-family: $rubik;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: $white;
  min-height: 100vh;
  color: $black;
  padding-bottom: 50px;
}

.text-black {
  color: $black;
}

.my-button {
  @include button-primary();
}

.my-button-success {
  @include button-primary($success);
}

.my-button-error {
  @include button-primary($error);
}
</style>
