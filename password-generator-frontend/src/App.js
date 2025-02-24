import React, { useState } from 'react';

function App() {
  const [password, setPassword] = useState('');
  const [length, setLength] = useState(8);

  const generatePassword = async () => {
      try {
          const response = await fetch(`http://localhost:8080/generate-password?length=${length}`);
          if (!response.ok) {
              throw new Error('Network response was not ok');
          }
          const data = await response.text();
          setPassword(data);
      } catch (error) {
          console.error('Failed to fetch:', error);
      }
  };

  return (
    <div>
      <h1>Générateur de Mot de Passe</h1>
      <div>
        <label>Longueur: </label>
        <input
          type="number"
          value={length}
          onChange={(e) => setLength(e.target.value)}
        />
      </div>
      <button onClick={generatePassword}>Générer</button>
      <div>
        <h2>Mot de Passe: {password}</h2>
      </div>
    </div>
  );
}

export default App;
