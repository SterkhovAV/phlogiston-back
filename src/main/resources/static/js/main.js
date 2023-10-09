let storage = loadLocalStorage()

function createTodoItem(name, id) {
    let item = document.createElement('li')

    let buttonGroup = document.createElement('div')
    let doneButton = document.createElement('button')
    let deleteButton = document.createElement('button')

    item.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center')
    item.setAttribute("data-id", id);
    item.textContent = name

    buttonGroup.classList.add('btn-group', 'btn-group-sm')
    doneButton.classList.add('btn', 'btn-success')
    doneButton.textContent = 'Готово'
    deleteButton.classList.add('btn', 'btn-danger')
    deleteButton.textContent = 'Удалить'

    buttonGroup.append(doneButton)
    buttonGroup.append(deleteButton)
    item.append(buttonGroup)

    return {
        item,
        doneButton,
        deleteButton,
    }
}

function createAppTitle(title) {
    let appTitle = document.createElement('h2')
    appTitle.innerHTML = title
    return appTitle
}

function createTodoItemForm() {
    let form = document.createElement('form')
    let input = document.createElement('input')
    let buttonWrapper = document.createElement('div')
    let button = document.createElement('button')

    form.classList.add('input-group', 'mb-3')
    input.classList.add('form-control')
    input.placeholder = 'Введите название нового дела'
    buttonWrapper.classList.add('input-group-append')
    button.classList.add('btn', 'btn-primary')
    button.textContent = 'Добавить дело'
    button.disabled = true

    buttonWrapper.append(button)
    form.append(input)
    form.append(buttonWrapper)

    return {
        form,
        input,
        button
    }
}

function createTodoList() {
    let list = document.createElement('ul')
    list.classList.add('list-group')
    return list
}

function createTodoElement(name, id, status = false) {
    let todoItem = createTodoItem(name, id)

    if (status) todoItem.item.classList.toggle('list-group-item-success')

    todoItem.doneButton.addEventListener('click', function () {
        todoItem.item.classList.toggle('list-group-item-success')
        updateStatus(todoItem.item.getAttribute('data-id'))
    })

    todoItem.deleteButton.addEventListener('click', function () {
        if (confirm('Вы уверены?')) todoItem.item.remove()
        removeFromStorage(todoItem.item.getAttribute('data-id'))
    })

    return todoItem.item
}

function loadLocalStorage() {
    let cart = localStorage.getItem("todoList")
    cart = cart ? JSON.parse(cart) : null
    return cart || []
}

function findMaxId(array) {
    let maxId = 0;
    array.forEach(todo => {
        if (todo.id > maxId) {
            maxId = todo.id;
        }
    })
    return maxId
}

function addNewToStorage(array, name, id) {

    let element = {
        id: id,
        name: name,
        status: false
    }
    array.push(element)
    localStorage.setItem('todoList', JSON.stringify(array))
}

function removeFromStorage(id) {
    storage = storage.filter(todo => todo.id.toString() !== id.toString())
    localStorage.setItem("todoList", JSON.stringify(storage))
}

function updateStatus(id) {
    let todoToUpdate = storage.find(todo => todo.id.toString() === id.toString());
    todoToUpdate.status = !todoToUpdate.status;
    localStorage.setItem("todoList", JSON.stringify(storage));
}

function createTodoApp() {
    let container = document.getElementById('todo-app')


    let todoAppTitle = createAppTitle('Список дел')
    let todoItemForm = createTodoItemForm()
    let todoList = createTodoList()

    container.append(todoAppTitle)
    container.append(todoItemForm.form)
    container.append(todoList)


    storage.forEach(it => todoList.append(createTodoElement(it.name, it.id, it.status)))


    todoItemForm.input.addEventListener('input', function () {
        todoItemForm.button.disabled = todoItemForm.input.value.trim() === ''
    })

    todoItemForm.form.addEventListener('submit', function (e) {
        e.preventDefault()
        if (todoItemForm.input.value.length > 0) {
            let id = findMaxId(storage) + 1
            todoList.append(createTodoElement(todoItemForm.input.value, id))
            addNewToStorage(storage, todoItemForm.input.value, id)
        }

        todoItemForm.button.disabled = true
        todoItemForm.input.value = ''
    })
}

(function () {
    document.addEventListener('DOMContentLoaded', createTodoApp())
})()



