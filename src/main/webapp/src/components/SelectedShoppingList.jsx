import Items from "./Items";
import './styles.css';
import Button from "./Button.jsx";

export default function SelectedShoppingList({shoppingList}) {
  if (!shoppingList) {
    return <div>Something went wrong</div>;
  }

  const handleDelete = async () => {
    try {
      const response = await fetch("http://localhost:8080/shoppingListByUser", {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("auth"),
        },
        body: JSON.stringify({
          id: shoppingList.id,
        }),
      });

      if (response.ok) {
        window.location.reload();
      } else {
        console.error("Failed to delete shopping list:", response.statusText);
      }
    } catch (error) {
      console.error("Error deleting shopping list:", error);
    }
  };

  return (
      <div className="selectedShoppingListDiv">
        <header className="selectedShoppingListHeader">
          <div className="selectedShoppingListDiv2">
            <h1 className="selectedShoppingListH1">{shoppingList.name}</h1>
            <Button className="button" onClick={handleDelete}>Delete</Button>
          </div>
          <div>
            <pre>{shoppingList.description}</pre>
          </div>
        </header>
        <Items selectedShoppingListId={shoppingList.id}/>
      </div>
  );
}
