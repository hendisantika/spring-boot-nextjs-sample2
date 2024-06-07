"use client";
import {useState} from "react";
import style from "./create.module.scss";
import api from "@/services/api";
import {Modal} from "@/components/Modal";

export default function Create() {
    const [price, setPrice] = useState();
    const [nameProduct, setNameProduct] = useState("");
    const [descriptionProduct, setDescriptionProduct] = useState("");
    const [open, setOpen] = useState<boolean>(false);

    const handleChange = (e: any) => {
        const value = e.target.value.replace(/\D/g, "");
        setPrice(value);
    };

    return (
        <div className={style.container}>
            <h1>Register a new product!</h1>

            <Modal isOpen={open} setOpen={setOpen}/>

            <div className={style.nameProduct}>
                <span>Name: </span>
                <input
                    type="text"
                    value={nameProduct}
                    onChange={(e) => {
                        setNameProduct(e.target.value);
                    }}
                    placeholder="Product's name!"
                />
            </div>
            <div className={style.priceProduct}>
                <span>Price: </span>{" "}
                <input
                    value={price}
                    onChange={handleChange}
                    placeholder="Enter the value of the product in IDR"
                    min="0"
                />
            </div>
            <div className={style.descriptionProduct}>
                <span>Description:</span>
                <input
                    type="text"
                    value={descriptionProduct}
                    onChange={(e) => {
                        setDescriptionProduct(e.target.value);
                    }}
                    placeholder="Tell us a little about your product and its benefits"
                />
            </div>
            <div>
                <button
                    onClick={() => {
                        try {
                            if (
                                nameProduct == "" ||
                                price == undefined ||
                                descriptionProduct == ""
                            ) {
                                return alert("All fields must be filled");
                            }

                            setOpen(!open);
                            setNameProduct("");
                            setDescriptionProduct("");
                            setPrice("");
                            return api.post("/products", {
                                name: nameProduct,
                                price: price * 100,
                                description: descriptionProduct,
                            });
                        } catch (error) {
                            console.log(error);
                        }
                    }}
                >
                    <span>Register Product</span>
                    <i></i>
                </button>
            </div>
        </div>
    );
}
