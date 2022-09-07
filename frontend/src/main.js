import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import fontawesome from "@/plugins/fontawesome";
import axios from "axios";
import { createI18n } from "vue-i18n";
import messages from "@/i18n/messages";

fontawesome();
axios.defaults.baseURL = "http://localhost:8080/api";

const i18n = createI18n({
  locale: "en",
  fallbackLocale: "en",
  messages,
});

createApp(App)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(router)
  .use(i18n)
  .mount("#app");
