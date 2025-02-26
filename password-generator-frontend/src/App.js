import React, { useState } from 'react';

function App() {
  const [password, setPassword] = useState('');
  const [length, setLength] = useState(8);

  // Mettre à jour l'URL pour utiliser ton backend sur Koyeb
  const BACKEND_URL = 'https://spatial-lisa-damienrepos-0c9b5e3f.koyeb.app';

  const generatePassword = async () => {
     try {
         console.log("Sending request to backend...");

         const response = await fetch(`${BACKEND_URL}/generate-password?length=${length}`);
         console.log("Response status:", response.status);  // Log de la réponse

         if (!response.ok) {
             throw new Error('Network response was not ok');
         }

         const data = await response.text();
         console.log("Received password:", data);  // Vérifie ce qui est reçu
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
