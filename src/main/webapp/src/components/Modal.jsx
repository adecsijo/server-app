import { forwardRef, useImperativeHandle, useRef } from "react";
import { createPortal } from "react-dom";
import Button from "./Button";
import './styles.css'

const Modal = forwardRef(function Modal({ children, buttonCaption }, ref) {
  const dialog = useRef();

  useImperativeHandle(ref, () => {
    return {
      open() {
        dialog.current.showModal();
      }
    };
  });

  return createPortal (
      <dialog ref={dialog} className="modalDialog">
        {children}
        <form method="dialog" className="modalForm">
          <Button>{buttonCaption}</Button>
        </form>
      </dialog>,
      document.getElementById('modal-root')
  );
})

export default Modal;