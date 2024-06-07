interface IModal {
    isOpen: boolean;
    setOpen: (isOpen: boolean) => void;
    productId: number;
    productName: string;
    productPrice: number;
    productDescription: string;
}

import api from "@/services/api";
import style from "./Modal.module.scss";
import {useState} from "react";

function refreshPage() {
    window.location.reload();
}

export function UpdateModal({
                                isOpen,
                                setOpen,
                                productId,
                                productName,
                                productPrice,
                                productDescription,
                            }: IModal) {
    if (isOpen) {
        const [price, setPrice] = useState<number>(productPrice);
        const [nameProduct, setNameProduct] = useState(productName);
        const [descriptionProduct, setDescriptionProduct] =
            useState(productDescription);

        const handleChange = (e: any) => {
            const value = e.target.value.replace(/\D/g, "");
            setPrice(value);
        };

        return (
            <div className={style.background}>
                <div className={style.modal}>
                    <h2>Edit: {productName}</h2>
                    <div className={style.formUpdate}>
                        <div className={style.nameProduct}>
                            <span>Name </span>
                            <input
                                type="text"
                                placeholder="edit product name"
                                value={nameProduct}
                                onChange={(e) => {
                                    setNameProduct(e.target.value);
                                }}
                            />
                        </div>

                        <div className={style.priceProduct}>
                            <span>Price </span>
                            <input
                                placeholder="edit product price"
                                value={price}
                                onChange={handleChange}
                                min="0"
                            />
                        </div>

                        <div className={style.descriptionProduct}>
                            <span>Description </span>
                            <input
                                type="text"
                                placeholder="edit product description"
                                value={descriptionProduct}
                                onChange={(e) => {
                                    setDescriptionProduct(e.target.value);
                                }}
                            />
                        </div>
                    </div>
                    <div className={style.buttonsContainer}>
                        <button
                            onClick={() => {
                                setOpen(!open);

                                api
                                    .put(`/products/${productId}`, {
                                        name: nameProduct,
                                        price: price * 100,
                                        description: descriptionProduct,
                                    })
                                    .then((response) => {
                                        refreshPage();
                                    })
                                    .catch((error) => {
                                        console.error(error);
                                    });
                            }}
                        >
                            <span>Confirm</span>
                        </button>
                        <button
                            onClick={() => {
                                setOpen(!open);
                            }}
                        >
                            <span>Cancel</span>
                        </button>
                    </div>
                </div>
            </div>
        );
    } else {
        <></>;
    }
}
