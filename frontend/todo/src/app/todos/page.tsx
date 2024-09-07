'use client';

import { useEffect, useState, FormEvent } from 'react';
import { ApolloClient, InMemoryCache, ApolloProvider, gql, useMutation, useQuery } from '@apollo/client';

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache(),
});

const GET_TASKS = gql`
  query GetTasks {
    tasks {
      id
      content
      priority
      status
      deadline
      description
      type
    }
  }
`;

const CREATE_TASK = gql`
  mutation CreateTask($input: TaskInput!) {
    createTask(input: $input) {
      id
      content
      priority
      status
      deadline
      description
      type
    }
  }
`;

interface Todo {
  id: number;
  content: string;
  priority: string;
  status: string;
  deadline: string;
  description: string;
  type: string;
}

interface NewTodo {
  content: string;
  priority: string;
  status: string;
  deadline: string;
  description: string;
  type: string;
}

function TodoList() {
  const [newTodo, setNewTodo] = useState<NewTodo>({
    content: '',
    priority: 'NORMAL',
    status: 'TODO',
    deadline: '',
    description: '',
    type: '',
  });

  const { loading, error, data, refetch } = useQuery(GET_TASKS);
  const [createTask] = useMutation(CREATE_TASK);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setNewTodo(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await createTask({ variables: { input: newTodo } });
      setNewTodo({
        content: '',
        priority: 'NORMAL',
        status: 'TODO',
        deadline: '',
        description: '',
        type: '',
      });
      refetch();
    } catch (err) {
      console.error('Failed to create new task', err);
    }
  };

  if (loading) return <p className="text-center text-2xl mt-10">Loading...</p>;
  if (error) return <p className="text-center text-2xl mt-10 text-red-500">Error: {error.message}</p>;

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-6">Todo List</h1>
      
      <form onSubmit={handleSubmit} className="mb-8 bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div className="mb-4">
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            type="text"
            name="content"
            value={newTodo.content}
            onChange={handleInputChange}
            placeholder="Task content"
            required
          />
        </div>
        <div className="mb-4 flex space-x-4">
          <select 
            className="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline"
            name="priority" 
            value={newTodo.priority} 
            onChange={handleInputChange}
          >
            <option value="HIGHEST">Highest</option>
            <option value="HIGH">High</option>
            <option value="NORMAL">Normal</option>
            <option value="LOW">Low</option>
            <option value="LOWER">Lower</option>
          </select>
          <select 
            className="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline"
            name="status" 
            value={newTodo.status} 
            onChange={handleInputChange}
          >
            <option value="TODO">To Do</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="DONE">Done</option>
          </select>
        </div>
        <div className="mb-4">
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            type="date"
            name="deadline"
            value={newTodo.deadline}
            onChange={handleInputChange}
          />
        </div>
        <div className="mb-4">
          <textarea
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            name="description"
            value={newTodo.description}
            onChange={handleInputChange}
            placeholder="Description"
          />
        </div>
        <div className="mb-4">
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            type="text"
            name="type"
            value={newTodo.type}
            onChange={handleInputChange}
            placeholder="Task type"
          />
        </div>
        <div className="flex items-center justify-between">
          <button 
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
            type="submit"
          >
            Add Task
          </button>
        </div>
      </form>

      <div className="overflow-x-auto">
        <table className="min-w-full bg-white">
          <thead className="bg-gray-800 text-white">
            <tr>
              <th className="w-1/3 text-left py-3 px-4 uppercase font-semibold text-sm">Content</th>
              <th className="w-1/6 text-left py-3 px-4 uppercase font-semibold text-sm">Priority</th>
              <th className="w-1/6 text-left py-3 px-4 uppercase font-semibold text-sm">Status</th>
              <th className="w-1/6 text-left py-3 px-4 uppercase font-semibold text-sm">Deadline</th>
              <th className="w-1/6 text-left py-3 px-4 uppercase font-semibold text-sm">Type</th>
            </tr>
          </thead>
          <tbody className="text-gray-700">
            {data.tasks.map((todo: Todo) => (
              <tr key={todo.id}>
                <td className="w-1/3 text-left py-3 px-4">{todo.content}</td>
                <td className="w-1/6 text-left py-3 px-4">{todo.priority}</td>
                <td className="w-1/6 text-left py-3 px-4">{todo.status}</td>
                <td className="w-1/6 text-left py-3 px-4">{todo.deadline}</td>
                <td className="w-1/6 text-left py-3 px-4">{todo.type}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default function Todos() {
  return (
    <ApolloProvider client={client}>
      <TodoList />
    </ApolloProvider>
  );
}
