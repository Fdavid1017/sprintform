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
  @include panel-small();

  $transition: all 0.15s ease-in;

  font-size: 20px;
  font-weight: 700;
  padding: 5px 30px;
  transition: $transition;
  color: $black;
  position: relative;
  overflow: hidden;
  z-index: 1;

  &:hover {
    box-shadow: 5px 5px 0px $black;
  }
}
</style>
