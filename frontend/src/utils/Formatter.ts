export function convertToMoney(value: number) {
    return value.toLocaleString("en-id", {
        style: "currency",
        currency: "IDR",
    });
}
