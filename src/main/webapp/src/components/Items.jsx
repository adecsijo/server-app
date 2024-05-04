import { useContext, useEffect, useState } from "react";
import NewItem from "./NewItem";
import './styles.css'
import Button from "./Button.jsx";
import { ItemsContext } from "./ItemsContext.jsx";

export default function Items({ selectedShoppingListId }) {
  const [items, setItems] = useState([]);

  const { onAddItem } = useContext(ItemsContext);

  const handleDeleteItem = async (itemId) => {
    try {
      const response = await fetch(`http://localhost:8080/item`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("auth"),
        },
        body: JSON.stringify({
          id: itemId
        })
      });
      if (response.ok) {
        setItems(items.filter(item => item.id !== itemId));
      } else {
        console.error("Failed to delete item:", response.statusText);
      }
    } catch (error) {
      console.error("Error deleting item:", error);
    }
  };

  useEffect(() => {
    const fetchShoppingListItems = async () => {
      try {
        const response = await fetch(`http://localhost:8080/item/${selectedShoppingListId}`, {
          headers: {
            Authorization: localStorage.getItem("auth"),
          }
        });
        if (response.ok) {
          const data = await response.json();
          setItems(data);
        } else {
          console.error("Failed to fetch shopping lists:", response.statusText);
        }
      } catch (error) {
        console.error("Error fetching shopping lists:", error);
      }
    };

    fetchShoppingListItems();
  }, [selectedShoppingListId, onAddItem, handleDeleteItem]);

  return (
      <section>
        <h2 className="itemsH2">Items</h2>
        <NewItem onAddItem={onAddItem} listId={selectedShoppingListId} />
        {items.length === 0 && (<p>This shopping list does not have any items yet.</p>)}
        {items.length > 0 && (
            <ul className="itemsUl">
              {items.map((item) => (
                  <li key={item.id} className="itemsLi">
                    <span>{item.name}</span>
                    <Button className="button" onClick={() => handleDeleteItem(item.id)}>Clear</Button>
                  </li>
              ))}
            </ul>
        )}
      </section>
  );
}
