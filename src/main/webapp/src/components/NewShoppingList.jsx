import { useContext, useRef } from "react";
import Input from "./Input";
import Modal from "./Modal";
import { ItemsContext } from "./ItemsContext.jsx";
import './styles.css'

export default function NewShoppingList() {
  const title = useRef();
  const description = useRef();
  const modal = useRef();

  const { onCancel } = useContext(ItemsContext);

  async function handleSave() {
    const enteredTitle = title.current.value;
    const enteredDescription = description.current.value;

    if (enteredTitle.trim() === '' || enteredDescription.trim() === '') {
      modal.current.open();
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/shoppingListByUser", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("auth"),
        },
        body: JSON.stringify({
          name: enteredTitle,
          description: enteredDescription,
        }),
      });

      if (response.ok) {
        window.location.reload();
      } else {
        console.error("Failed to add shopping list:", response.statusText);
      }
    } catch (error) {
      console.error("Error adding shopping list:", error);
    }
  }

  return (
      <>
        <Modal ref={modal} buttonCaption='Close'>
          <h2 className='newShoppingListH2'>Invalid Input</h2>
          <p className='newShoppingListParagraph'>Oops... looks like you forgot to enter a value.</p>
          <p className='newShoppingListParagraph'>Please make sure you provide a valid value for every input field.</p>
        </Modal>
        <div className="container">
          <div>
            <div>
              <Input className="inputParagraph" ref={title} label='Title' type="text"/>
              <Input className="inputParagraph" ref={description} label='Description' textarea/>
            </div>
            <menu className="newShoppingListMenu">
              <li style={{listStyleType: 'none'}}>
                <button onClick={onCancel}>Cancel</button>
              </li>
              <li style={{listStyleType: 'none'}}>
                <button onClick={handleSave}>Save</button>
              </li>
            </menu>
          </div>
        </div>
      </>
  );
}
