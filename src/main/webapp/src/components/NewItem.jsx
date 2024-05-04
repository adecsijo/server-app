import { useState } from "react";
import './styles.css'

export default function NewItem({ listId, onAddItem }) {
  const [enteredItem, setEnteredItem] = useState('');

  function handleChange(event) {
    setEnteredItem(event.target.value);
  }

  async function handleClick() {
    if (enteredItem.trim() === '') {
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/item/${listId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("auth"),
        },
        body: JSON.stringify({
          name: enteredItem,
        }),
      });

      if (response.ok) {
        onAddItem(enteredItem);
        setEnteredItem('');
      } else {
        console.error("Failed to add item:", response.statusText);
      }
    } catch (error) {
      console.error("Error adding item:", error);
    }
  }

  return (
      <div className="newItemDiv">
        <input type="text" className="newItemInputField" onChange={handleChange} value={enteredItem} />
        <button className="button" onClick={handleClick}>Add Item</button>
      </div>
  );
}
