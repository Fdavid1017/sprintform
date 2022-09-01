import axios from "axios";

export async function getTransactions() {
  const data = await axios({
    url: `/transactions.json`,
    method: "GET",
  });

  return data.data;
}
