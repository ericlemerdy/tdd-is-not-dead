json.array!(@employees) do |employee|
  json.extract! employee, :id, :name, :email
  json.url employee_url(employee, format: :json)
end
