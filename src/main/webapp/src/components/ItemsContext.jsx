import { createContext } from "react";

export const ItemsContext = createContext({
  onAdd: () => {},
  onCancel: () => {},
  onStartAddShoppingList: () => {},
  items: [],
  onDelete: () => {},
  onAddItem: () => {},
  onDeleteItem: () => {},
  onStartAddShoppinList: () => {},
  shoppingLists: undefined,
  onSelectShoppingList: () => {},
  selectedShoppingListId: undefined,
});
