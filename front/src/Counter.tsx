import React, { useState } from 'react';

function Counter(){
    const [count, setCount] = useState<number>(0);
    return (
        <div>
            <h2>Counter</h2>
            <button onClick={() => setCount(count + 1)}>+</button>
            <button onClick={() => setCount(count - 1)}>-</button>
            <p>Count: {count}</p>
        </div>
    );   
}

export default Counter;