import {useContext, useEffect, useState} from "react";
import Button from "./Button";
import { ItemsContext } from "./ItemsContext.jsx";
import './styles.css'

export default function ShoppingListsSidebar() {
  const { onStartAddShoppinList, onSelectShoppingList, selectedShoppingListId} = useContext(ItemsContext);
  const [shoppingLists, setShoppingLists] = useState([]);

  useEffect(() => {
    const fetchShoppingLists = async () => {
      try {
        const response = await fetch("http://localhost:8080/shoppingListByUser", {
          headers: {
            Authorization: localStorage.getItem("auth"),
          }
        });
        if (response.ok) {
          const data = await response.json();
          setShoppingLists(data);
        } else {
          console.error("Failed to fetch shopping lists:", response.statusText);
        }
      } catch (error) {
        console.error("Error fetching shopping lists:", error);
      }
    };

    fetchShoppingLists();
  }, []);

  return (
      <aside className="shoppingListsSidebarAside">
        <h2 className="shoppingListsSidebarH2">Your Shopping Lists</h2>
        <div>
          <Button onClick={onStartAddShoppinList}>+ Add Shopping List</Button>
        </div>
        <ul className="shoppingListsSidebarUl">
          {shoppingLists.map(item => {
            return (
                <li key={item.id}>
                  <button
                      onClick={() => onSelectShoppingList(item.id)}
                      className={item.id === selectedShoppingListId ? 'shoppingListsSidebarTrue' : 'shoppingListsSidebarFalse'}>
                    {item.name}
                  </button>
                </li>
            )
          })}
        </ul>
      </aside>
  )
}