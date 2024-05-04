import React, { useContext } from "react";
import { AuthContext } from "./AuthProvider.jsx"; // Import the AuthProvider
import NewShoppingList from "./NewShoppingList";
import NoShoppingListSelected from "./NoShoppingListSelected";
import SelectedShoppingList from "./SelectedShoppingList";
import './styles.css'

const MainContent = ({ selectedShoppingList, selectedShoppingListId }) => {
  const { isLoggedIn } = useContext(AuthContext);
  if (!isLoggedIn) {
    return (
        <div>
          <h2>Please log in to continue.</h2>
        </div>
    );
  }

  if (selectedShoppingListId === null) {
    return <NewShoppingList />;
  } else if (selectedShoppingListId === undefined) {
    return <NoShoppingListSelected />;
  } else {
    return <SelectedShoppingList shoppingList={selectedShoppingList} />;
  }
};

export default MainContent;
