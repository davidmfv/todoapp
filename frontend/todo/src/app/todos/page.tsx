'use client';

import { useEffect, useState } from 'react';
import axios from 'axios';

interface Todo {
  id: number;
  content: string;
  priority: string;
  status: string;
  deadline: string;
  description: string;
  type: string;
}

const Todos: React.FC = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchTodos = async () => {
      try {
        const response = await axios.get<Todo[]>('http://localhost:8080/api/tasks');
        setTodos(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchTodos();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div>
      <h1>Todo List</h1>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            <h2>{todo.content}</h2>
            <p>Priority: {todo.priority}</p>
            <p>Status: {todo.status}</p>
            <p>Deadline: {todo.deadline}</p>
            <p>Description: {todo.description}</p>
            <p>Type: {todo.type}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Todos;
