import Pageable from "@/classes/pageable";
import axios from "axios";

export async function getTransactions(page = 0, size = 5) {
  const data = await axios({
    url: `/transactions`,
    method: "GET",
    params: {
      page,
      size,
    },
  });

  const response = data.data;

  const transactionsData = new Pageable(
    response.content,
    response.last,
    response.totalPages,
    response.totalElements,
    response.pageable.pageNumber,
    response.size,
    response.numberOfElements
  );

  return transactionsData;
}

export async function addTransaction(transaction) {
  const data = await axios({
    url: `/transactions`,
    method: "POST",
    data: transaction,
  });

  return data.data;
}

export async function editTransaction(transaction) {
  const data = await axios({
    url: `/transactions/${transaction.id}`,
    method: "PUT",
    data: transaction,
  });

  return data.data;
}

export async function getTransactionRanges() {
  const data = await axios({
    url: `/transactions/ranges`,
    method: "GET",
  });

  return data.data;
}

export async function searchTransaction(transactionFilter, page = 0, size = 5) {
  console.log(transactionFilter);
  const data = await axios({
    url: `/transactions/search`,
    method: "GET",
    params: {
      page,
      size,
    },
    data: {
      summary: "reggeli",
      category: null,
      sumMin: null,
      sumMax: null,
      paidStart: null,
      paidEnd: null,
    },
  });

  const response = data.data;

  const transactionsData = new Pageable(
    response.content,
    response.last,
    response.totalPages,
    response.totalElements,
    response.pageable.pageNumber,
    response.size,
    response.numberOfElements
  );

  return transactionsData;
}
