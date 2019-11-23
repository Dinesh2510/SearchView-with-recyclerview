# SearchView-with-recyclerview


![Screenshot_20191123-095834](https://user-images.githubusercontent.com/46309253/69473336-6f9f5a00-0dd9-11ea-883d-3c656f2796ef.png)

![Screenshot_20191123-095846](https://user-images.githubusercontent.com/46309253/69473337-6f9f5a00-0dd9-11ea-8239-9027b1bc7465.png)


## Search CharSequence

        this.Searchtext = (EditText) findViewById(R.id.search_input);
        this.Searchtext.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                MainActivity.this.filterQuery(editable.toString());
            }
        });

## Filter Query

    public void filterQuery(String text) {
        ArrayList<ExampleItem> filterdNames = new ArrayList<>();
        for (ExampleItem s : this.exampleList) {
            if (s.getText1().toLowerCase().contains(text) || s.getText2().toLowerCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.adapter.setFilter(filterdNames);
    }
    
## SetFilter in Adapter 

      public void setFilter(List<ExampleItem> filterdNames) {
        this.exampleList = filterdNames;
        notifyDataSetChanged();
       }
