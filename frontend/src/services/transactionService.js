import axios from "axios";

export async function getTransactions() {
  const data = await axios({
    url: `/transactions`,
    method: "GET",
  });

  return data.data;
}
