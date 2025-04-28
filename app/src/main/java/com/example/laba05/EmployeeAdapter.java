package com.example.laba05;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<Employee> list;
    private Runnable onListChanged;
    private OnItemClickListener onItemClickListener;  // Добавляем интерфейс для обработки кликов

    // Интерфейс для обработки кликов
    public interface OnItemClickListener {
        void onItemClick(Employee employee);
    }

    public EmployeeAdapter(List<Employee> list, Runnable onListChanged) {
        this.list = list;
        this.onListChanged = onListChanged;
    }

    // Метод для установки слушателя кликов
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvData;
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvData = itemView.findViewById(R.id.tvData);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            // Устанавливаем обработчик кликов для каждого элемента списка
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(list.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {
        Employee e = list.get(position);
        holder.tvData.setText("Таб. №: " + e.getNumber() + "\nФИО: " + e.getName()
                + "\nРазряд: " + e.getRank() + "\nСтавка: " + e.getRate());

        holder.btnDelete.setOnClickListener(v -> {
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
            onListChanged.run();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
