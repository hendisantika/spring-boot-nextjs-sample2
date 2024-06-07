"use client";
import style from "./update.module.scss";
import api from "@/services/api";
import {useEffect, useState} from "react";
import {ProductType} from "@/types/ProductType";
import {convertToMoney} from "@/utils/Formatter";
import {FaPen} from "react-icons/fa";
import {UpdateModal} from "@/components/UpdateModal";

export default function Home() {
    const [products, setProducts] = useState<ProductType[]>([]);
    const [open, setOpen] = useState<boolean>(false);
    const [selectedProductName, setSelectedProductName] = useState("");
    const [selectedProductPrice, setSelectedProductPrice] = useState(0);
    const [selectedProductDescription, setSelectedProductDescription] =
        useState("");
    const [productId, setProductId] = useState(0);

    async function getProducts() {
        const response = await api.get<ProductType[]>("/products", {});
        setProducts(response.data);
        return response.data;
    }

    useEffect(() => {
        getProducts();
    }, []);

    return (
        <>
            <div className={style.container}>
                <h1>Update products</h1>

                <div className={style.productsSection}>
                    {products.map((product) => (
                        <div className={style.productContainer} key={product.id}>
                            <div className={style.productTitle}>
                                <h2 className={style.productName}>{product.name}</h2>
                                <UpdateModal
                                    isOpen={open}
                                    setOpen={setOpen}
                                    productId={productId}
                                    productName={selectedProductName}
                                    productPrice={selectedProductPrice}
                                    productDescription={selectedProductDescription}
                                />
                                <div
                                    className={style.pencilIcon}
                                    onClick={() => {
                                        setOpen(!open);
                                        setSelectedProductName(product.name);
                                        setSelectedProductPrice(product.price);
                                        setSelectedProductDescription(product.description);
                                        setProductId(product.id);
                                    }}
                                >
                                    <FaPen/>
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
