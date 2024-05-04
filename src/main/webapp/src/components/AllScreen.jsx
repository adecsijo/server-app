import {useContext, useEffect, useState} from "react";
import ShoppingListsSidebar from "./ShoppingListsSidebar";
import { ItemsContext } from "./ItemsContext.jsx";
import MainContent from "./MainContent";
import './styles.css'
import {Link} from "react-router-dom";
import {AuthContext} from "./AuthProvider.jsx";
import Button from "./Button.jsx";

function AllScreen() {
  const [shoppingListState, setShoppingListState] = useState({
    selectedShoppingListId: undefined,
    shoppingLists: [],
    items: [],
  });

  const {logout} = useContext(AuthContext);

  const [setLoginError] = useState(null);

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
          setShoppingListState((prevState) => ({
            ...prevState,
            shoppingLists: data,
          }));
        } else {
          console.error("Failed to fetch shopping lists:", response.statusText);
        }
      } catch (error) {
        console.error("Error fetching shopping lists:", error);
      }
    };
    fetchShoppingLists();
  }, []);


  function handleAddItem(text) {
    setShoppingListState((prevState) => {
      const itemId = Math.random();
      const newItem = {
        text: text,
        shoppingListId: prevState.selectedShoppingListId,
        id: itemId
      };
      return {
        ...prevState,
        items: [newItem, ...prevState.items]
      };
    });
  }

  function handleDeleteItem(id) {
    setShoppingListState((prevState) => {
      return {
        ...prevState,
        items: prevState.items.filter((item) => item.id !== id),
      };
    });
  }

  function handleSelectShoppingList(id) {
    setShoppingListState((prevState) => {
      return {
        ...prevState,
        selectedShoppingListId: id,
      };
    });
  }

  function handleStartAddShoppingList() {
    setShoppingListState((prevState) => {
      return {
        ...prevState,
        selectedShoppingListId: null,
      };
    });
  }

  function handleCancelAddShoppigList() {
    setShoppingListState((prevState) => {
      return {
        ...prevState,
        selectedShoppingListId: undefined,
      };
    });
  }

  function handleAddShoppingList(shoppingListData) {
    setShoppingListState((prevState) => {
      const shoppingListId = Math.random();
      const newShoppingList = {
        ...shoppingListData,
        id: shoppingListId
      };
      return {
        ...prevState,
        selectedShoppingListId: undefined,
        shoppingLists: [...prevState.shoppingLists, newShoppingList]
      };
    });
  }

  function handleDeleteShoppingList() {
    setShoppingListState((prevState) => {
      return {
        ...prevState,
        selectedShoppingListId: undefined,
        shoppingLists: prevState.shoppingLists.filter(
            (shoppingList) => shoppingList.id !== prevState.selectedShoppingListId
        ),
      };
    });
  }

  const selectedShoppingList = shoppingListState.shoppingLists.find(shoppingList => shoppingList.id === shoppingListState.selectedShoppingListId);

  function getSelectedShoppingListItems(shoppingListState, selectedShoppingList) {
    const shoppingListItems = [];

    if(shoppingListState.selectedShoppingListId !== undefined) {
      for(const item of shoppingListState.items) {
        if(selectedShoppingList !== undefined && selectedShoppingList.id === item.shoppingListId) {
          shoppingListItems.push(item);
        }
      }
    }
    return shoppingListItems;
  }

  const itemCtx = {
    onAdd: handleAddShoppingList,
    onCancel: handleCancelAddShoppigList,
    onStartAddShoppingList: handleStartAddShoppingList,
    items: getSelectedShoppingListItems(shoppingListState, selectedShoppingList),
    onDelete: handleDeleteShoppingList,
    onAddItem: handleAddItem,
    onDeleteItem: handleDeleteItem,
    onStartAddShoppinList: handleStartAddShoppingList,
    shoppingLists: shoppingListState.shoppingLists,
    onSelectShoppingList: handleSelectShoppingList,
    selectedShoppingListId: shoppingListState.selectedShoppingListId,
  };

  const handleLogout = async (e) => {
    e.preventDefault();
    try {
      await logout();
    } catch (error) {
      setLoginError(error.message);
      console.log(error.message);
    }
  };

  return (
      <>
        <ItemsContext.Provider value={itemCtx}>
          <main className="appMain">
            <ShoppingListsSidebar />
            <MainContent
                selectedShoppingList={selectedShoppingList}
                selectedShoppingListId={shoppingListState.selectedShoppingListId} />
          </main>
          <Link className="logout" to="/logout"><Button onClick={handleLogout}>Logout</Button></Link>
        </ItemsContext.Provider>
      </>
  );
}

export default AllScreen;
