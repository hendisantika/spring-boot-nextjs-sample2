"use client";
import style from "./delete.module.scss";
import api from "@/services/api";
import {useEffect, useState} from "react";
import {ProductType} from "@/types/ProductType";
import {convertToMoney} from "@/utils/Formatter";
import {FaTrash} from "react-icons/fa";
import {DeleteModal} from "@/components/DeleteModal";

export default function Home() {
    const [products, setProducts] = useState<ProductType[]>([]);
    const [open, setOpen] = useState<boolean>(false);
    const [selectedProduct, setSelectedProduct] = useState("");
    const [productId, setProductId] = useState(0);

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
                <h1>Delete products</h1>

                <div className={style.productsSection}>
                    {products.map((product) => (
                        <div className={style.productContainer} key={product.id}>
                            <div className={style.productTitle}>
                                <h2 className={style.productName}>{product.name}</h2>
                                <DeleteModal
                                    isOpen={open}
                                    setOpen={setOpen}
                                    productName={selectedProduct}
                                    productId={productId}
                                />
                                <div
                                    className={style.trashIcon}
                                    onClick={() => {
                                        setOpen(!open);
                                        setSelectedProduct(product.name);
                                        setProductId(product.id);
                                    }}
                                >
                                    <FaTrash/>
                                </div>
                            </div>
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
                    ))}
                </div>
            </div>
        </>
    );
}
