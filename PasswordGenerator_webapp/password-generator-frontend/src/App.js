import React, { useState } from 'react';

function App() {
  const [password, setPassword] = useState('');
  const [length, setLength] = useState(8);

  const generatePassword = async () => {
      try {
          const response = await fetch('https://spatial-lisa-damienrepos-0c9b5e3f.koyeb.app');
          if (!response.ok) {
              throw new Error('Network response was not ok');
          }
          const data = await response.text();
          setPassword(data);
      } catch (error) {
          console.error('Failed to fetch:', error);
      }
  };

  // Fonction pour copier le mot de passe dans le presse-papiers
  const handleCopy = () => {
    navigator.clipboard.writeText(password)
      .then(() => {
        alert("Mot de passe copié dans le presse-papiers !");
      })
      .catch((error) => {
        console.error("Erreur lors de la copie : ", error);
      });
  };

  return (
    <div className="content">
      <h1>Générateur de Mot de Passe</h1>
      <button onClick={generatePassword}>Générer</button>
      <div className="password_content">
        <h2>Mot de Passe:</h2>
        <h2>{password}</h2>
        {/* Bouton pour copier le mot de passe */}
        <button onClick={handleCopy} disabled={!password}>
          Copier
        </button>
      </div>
    </div>
  );
}

export default App;
