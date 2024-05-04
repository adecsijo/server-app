import { forwardRef } from "react";
import './styles.css'

const Input = forwardRef(
    function Input({ label, textarea, ...props }, ref) {
      return (
          <p className="inputParagraph">
            <label className="inputLabel">{label}</label>
            {textarea ? <textarea ref={ref} className="inputField" {...props}/> : <input ref={ref} className="inputField" {...props} />}
          </p>
      );
    }
)

export default Input;