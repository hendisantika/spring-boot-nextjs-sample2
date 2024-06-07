interface IModal {
    isOpen: boolean;
    setOpen: (isOpen: boolean) => void;
    productName: string;
    productId: number;
}

import api from "@/services/api";
import style from "./Modal.module.scss";

function refreshPage() {
    window.location.reload();
}

export function DeleteModal({
                                isOpen,
                                setOpen,
                                productName,
                                productId,
                            }: IModal) {
    if (isOpen) {
        return (
            <div className={style.background}>
                <div className={style.modal}>
                    <h2>Do you really want to delete {productName}</h2>
                    <div className={style.buttonsContainer}>
                        <button
                            onClick={() => {
                                setOpen(!open);
                                console.log(productId);
                                api
                                    .delete(`/products/${productId}`)
                                    .then((response) => {
                                        console.log(`Deleted post with ID ${productId}`);
                                        refreshPage();
                                    })
                                    .catch((error) => {
                                        console.error(error);
                                    });
                            }}
                        >
                            <span>Delete</span>
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
