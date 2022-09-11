<template>
  <div>
    <div v-if="isLoading" class="text-center">
      <div class="spinner-border" role="status"></div>
    </div>

    <div class="filter-container">
      <div class="row w-100 mx-0 mb-4">
        <div class="col-12 col-md-6 d-flex align-items-end pb-4">
          <my-input
            v-model="summaryFilter"
            :label="$t('form.summary')"
            id="search-text"
            class="w-100"
          />
        </div>

        <div class="col-12 col-md-6 d-flex align-items-end pb-4">
          <div class="w-100">
            <label class="input-label" for="dp-input-date-range-picker">
              {{ $t("form.date") }}
            </label>
            <Datepicker
              v-model="datePicker"
              :presetRanges="presetRanges"
              :enable-time-picker="false"
              class="date-picker"
              uid="date-range-picker"
              range
              text-input
              clearable
              auto-apply
            />
          </div>
        </div>

        <div class="col-12 col-md-6 d-flex align-items-end pb-4">
          <my-select
            v-model="category"
            :options="categories"
            :label="$t('category.category')"
            id="category-input"
            class="w-100"
            required
          />
        </div>

        <div class="col-12 col-md-6 d-flex align-items-end pb-4">
          <div class="w-100">
            <div class="input-label">
              {{ $t("form.sum") }}
            </div>

            <div
              class="w-100 d-flex align-items-center justify-content-between"
            >
              <my-input
                type="number"
                v-model="sumRange[0]"
                class="sum-range-input"
              />

              <div class="w-100 mx-4">
                <slider
                  v-model="sumRange"
                  :min="filterRange.sumMin"
                  :max="filterRange.sumMax"
                />
              </div>

              <my-input
                type="number"
                v-model="sumRange[1]"
                class="sum-range-input"
              />
            </div>
          </div>
        </div>
      </div>

      <div class="d-flex justify-content-end">
        <button class="my-button-error me-4" @click="resetFilter()">
          {{ $t("form.clear") }}
        </button>

        <button class="my-button-success" @click="search()">
          {{ $t("form.search") }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import MyInput from "@/components/MyInput";
import Slider from "@vueform/slider";
import Datepicker from "@vuepic/vue-datepicker";
import MySelect from "./MySelect.vue";
import "@vuepic/vue-datepicker/dist/main.css";
import { ref, onMounted, defineEmits } from "vue";
import { getTransactionRanges } from "@/services/transactionService";
import TransactionCategoryEnum from "@/enums/TransactionCategoryEnum";

const emits = defineEmits(["search"]);

const summaryFilter = ref("");
const sumRange = ref([0, 10]);
const isLoading = ref(false);
const datePicker = ref(null);
const category = ref(null);
const filterRange = ref({
  sumMin: 0,
  sumMax: 0,
  paidMin: new Date(),
  paidMax: new Date(),
});

const categories = Object.keys(TransactionCategoryEnum).map((key) => {
  return {
    key,
    text: TransactionCategoryEnum[key],
  };
});

function resetFilter() {
  summaryFilter.value = "";
  sumRange.value = [filterRange.value.sumMin, filterRange.value.sumMax];
  datePicker.value = null;
  category.value = null;
}

function search() {
  const filterValues = {};

  if (summaryFilter.value) {
    filterValues["summary"] = summaryFilter.value;
  }

  if (category.value) {
    filterValues["category"] = category.value;
  }

  if (sumRange.value?.length > 0) {
    filterValues["sumMin"] = sumRange.value[0];
    filterValues["sumMax"] = sumRange.value[1];
  }

  if (datePicker.value?.length > 0) {
    if (datePicker.value[0].getTime() === datePicker.value[1].getTime()) {
      datePicker.value[0].setHours(0, 0, 0, 0);
      datePicker.value[1].setHours(23, 59, 59, 999);
    } else {
      datePicker.value[0].setHours(0, 0, 0, 0);
      datePicker.value[1].setHours(0, 0, 0, 0);
    }

    filterValues["paidStart"] = datePicker.value[0].getTime();
    filterValues["paidEnd"] = datePicker.value[1].getTime();
  }

  emits("search", filterValues);
}

onMounted(() => {
  isLoading.value = true;
  getTransactionRanges()
    .then((ranges) => {
      filterRange.value = ranges;

      sumRange.value = [ranges.sumMin, ranges.sumMax];
    })
    .finally(() => {
      isLoading.value = false;
    });
});
</script>

<script>
import {
  endOfMonth,
  endOfYear,
  startOfMonth,
  startOfYear,
  subMonths,
} from "date-fns";

export default {
  computed: {
    presetRanges() {
      return [
        {
          label: this.$t("datePicker.today"),
          range: [new Date(), new Date()],
        },
        {
          label: this.$t("datePicker.thisMonth"),
          range: [startOfMonth(new Date()), endOfMonth(new Date())],
        },
        {
          label: this.$t("datePicker.lastMonth"),
          range: [
            startOfMonth(subMonths(new Date(), 1)),
            endOfMonth(subMonths(new Date(), 1)),
          ],
        },
        {
          label: this.$t("datePicker.thisYear"),
          range: [startOfYear(new Date()), endOfYear(new Date())],
        },
      ];
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../assets/scss/variables.scss";
@import "../assets/scss/mixins.scss";
@import "@vueform/slider/themes/default.css";

.filter-container {
  @include panel();

  padding: 10px;

  .input-label {
    font-weight: 700;
    font-size: 25px;
    color: #23252e;
  }

  .date-picker {
    & :deep(input) {
      @include panel-small();
      font-size: 18px;
      outline: none;
      transition: all 0.15s ease-in;

      &:hover,
      &:focus {
        box-shadow: 5px 5px 0 $black;
      }
    }
  }
}
</style>

<style lang="scss">
@import "../assets/scss/variables.scss";

.dp__range_end,
.dp__range_start,
.dp__active_date {
  background: $blue !important;
}
</style>
