import { useContext } from 'react';
import noListImg from '../assets/no-list.png'
import Button from './Button';
import { ItemsContext } from './ItemsContext.jsx';
import './styles.css'

export default function NoShoppingListSelected() {
  const {onStartAddShoppingList} = useContext(ItemsContext);
  return (
      <div className="noShoppingListSelectedDiv">
        <img src={noListImg} alt="An empty task list" className='noShoppingListSelectedImg' />
        <h2 className='noShoppingListSelectedH2'>No Shopping List Selected</h2>
        <p className='noShoppingListSelectedP1'>Select a shopping list or get started with a new one!</p>
        <p className='noShoppingListSelectedP2'>
          <Button onClick={onStartAddShoppingList}>Create new shopping list</Button>
        </p>
      </div>
  );
}