"use client";
import style from "./page.module.scss";
import api from "@/services/api";
import {useEffect, useState} from "react";
import {ProductType} from "@/types/ProductType";
import {convertToMoney} from "@/utils/Formatter";

export default function Home() {
    const [products, setProducts] = useState<ProductType[]>([]);

    async function getProducts() {
        const response = await api.get<ProductType[]>("/products", {});
        console.log(response.data);
        setProducts(response.data);
        return response.data;
    }

    useEffect(() => {
        getProducts();
    }, []);

    return (
        <>
            <div className={style.container}>
                <h1>Product list</h1>

                <div className={style.productsSection}>
                    {products.map((product) => (
                        <div className={style.productContainer}>
                            <div className={style.productTitle}>
                                <h2 className={style.productName}>{product.name}</h2>
                                <h2 className={style.productPrice}>
                                    {convertToMoney(product.price / 100)}
                                </h2>
                                <div className={style.productDescription}>
                                    <p>
                                        <span>Description: </span>
                                        {product.description}
                                    </p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
}
