import React, { useState, FormEvent, ChangeEvent } from 'react';

function NameForm(){
    const [name, setName] = useState<string>(""); 
    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (name.trim() === "") {
            alert("Please input your name");
        } else {
            alert("Hello, " + name);
        }
    }
const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input placeholder="input your name" value={name} onChange={handleChange}/>
            <button type="submit">Submit</button>
        </form>
    );
}

export default NameForm;