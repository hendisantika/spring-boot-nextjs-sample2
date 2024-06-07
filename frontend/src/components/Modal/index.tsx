interface IModal {
    isOpen: boolean;
    setOpen: (isOpen: boolean) => void;
}

import style from "./Modal.module.scss";

export function Modal({isOpen, setOpen}: IModal) {
    if (isOpen) {
        return (
            <div className={style.background}>
                <div className={style.modal}>
                    <h2>Product created successfully!</h2>
                    <button onClick={() => setOpen(!open)}>
                        <span>Close</span>
                    </button>
                </div>
            </div>
        );
    } else {
        <></>;
    }
}
